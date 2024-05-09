import { useState } from 'react';
import { useCartManager } from '../../contexts/cart-context';
import { ProductPreview } from './ProductList';
import ProductDetail from './ProductDetail';

const ProductCard = (props: ProductPreview) => {
  const { id, koreanName, englishName, imageUrlForList, price, options } =
    props;
  const { cart, addProduct } = useCartManager();
  const [showModal, setShowModal] = useState(false);

  const closeModal = () => {
    setShowModal(false);
  };

  console.log('CART: >>>>>>>>>', cart);

  return (
    <>
      <div
        role='button'
        className='flex-col'
        onClick={() => setShowModal(true)}
      >
        <img
          className='w-36 border rounded-xl'
          src={imageUrlForList}
          alt={koreanName}
        />
        <div className='text-md text-center font-semibold'>{koreanName}</div>
        <div className='text-s text-right'>{price}원</div>
      </div>
      {showModal && (
        <div
          className='fixed top-0 left-0 right-0 bottom-0 bg-gray-800 bg-opacity-50 flex items-center justify-center'

          // Todo 회색 바탕 누르면 꺼지게 만들기
          // onClick={(e) => {
          //   setShowModal(!showModal);
          // }}
        >
          <div className='bg-white p-8 rounded-lg relative w-3/4 h-1/2'>
            <button className='absolute top-4 right-4' onClick={closeModal}>
              X
            </button>

            <ProductDetail {...props} closeModal={closeModal} />
          </div>
        </div>
      )}
    </>
  );
};

export default ProductCard;
