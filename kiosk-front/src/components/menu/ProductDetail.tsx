import { useState } from 'react';
import { ProductOption } from '../../types/types';
import { useCartManager } from '../../contexts/cart-context';

type ProductDetailProps = {
  id: number;
  koreanName: string;
  englishName: string;
  koreanContent: string;
  englishContent: string;
  imageUrlForList: string;
  price: number;
  options: ProductOption[];
  closeModal: () => void;
};

const ProductDetail = (props: ProductDetailProps) => {
  const {
    id,
    koreanName,
    koreanContent,
    englishName,
    englishContent,
    imageUrlForList,
    price,
    options,
    closeModal,
  } = props;

  const [quantity, setQuantity] = useState(1);
  const [selectedOption, setSelectedOption] = useState(() => options[0]);

  const totalPrice = (price + selectedOption.addPrice) * quantity;

  const { cart, addProduct } = useCartManager();

  const plusQuantity = () => setQuantity(quantity + 1);
  const minusQuantity = () => setQuantity(Math.max(1, quantity - 1));
  const onClickOption = (idx: number) => setSelectedOption(options[idx]);
  // let totalPrice = 0;
  // useEffect(() => {
  //   totalPrice = (quantity + options[selectedOption].addPrice) * quantity;
  // }, [quantity, selectedOption]);

  console.log(cart);

  return (
    <div>
      <div className='flex gap-3'>
        <img
          className='w-6/12 rounded-xl max-w-80'
          src={imageUrlForList}
          alt='koreanName'
        />
        <div className='flex-col gap-2'>
          <p className='text-xl font-bold'>{koreanName}</p>
          <p className='text-pretty'>{koreanContent}</p>
          <p className='mt-3 ml'>{price}원</p>
          <p className='mt-4 ms-5 font-bold'>최종 금액: {totalPrice}</p>
        </div>
      </div>
      <div className='flex m-3 gap-4'>
        {options.map((option, idx) => {
          return (
            <button
              className='border border-amber-500 w-20 p-3'
              key={option.id}
              onClick={() => onClickOption(idx)}
            >
              <p className='text-sm'>{option.optionName}</p>
              <p className='text-sm'>
                {' '}
                {`${!option.addPrice ? '' : '+' + option.addPrice}`}
              </p>
            </button>
          );
        })}

        <span className='ml-5 flex'>
          <button
            onClick={minusQuantity}
            className='shadow-lg py-2 px-4 h-10 rounded-lg font-bold'
          >
            -
          </button>
          <p className='py-2 px-4'>수량 : {quantity}</p>
          <button
            onClick={plusQuantity}
            className='shadow-lg py-2 px-4 h-10 rounded-lg font-bold'
          >
            +
          </button>
        </span>
      </div>

      <div className='flex gap-3 justify-center'>
        <button
          className='bg-blue-400 p-3 rounded-lg'
          onClick={() => {
            addProduct({
              ...props,
              price: price + selectedOption.addPrice,
              quantity,
              optionName: selectedOption.optionName,
            });
            closeModal();
          }}
        >
          추가하기
        </button>
        <button className='bg-red-400 p-3 rounded-lg'>뒤로가기</button>
      </div>
    </div>
  );
};

export default ProductDetail;
