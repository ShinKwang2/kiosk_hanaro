import { useEffect, useState } from 'react';
import { useSelectedMenu } from '../../contexts/selectedMenu-context';
import { ApiResponse, ProductOption } from '../../types/types';
import ProductCard from './ProductCard';

export type ProductPreview = {
  id: number;
  koreanName: string;
  englishName: string;
  koreanContent: string;
  englishContent: string;
  imageUrlForList: string;
  price: number;
  options: ProductOption[];
};

const ProductList = () => {
  const [productPreviews, setProductPreviews] = useState<ProductPreview[]>([]);
  const { selectedMenu } = useSelectedMenu();

  console.log('>>>>>>ProductList 렌더링');

  useEffect(() => {
    const controller = new AbortController();
    const { signal } = controller;

    (async function () {
      const res = await fetch(
        `http://localhost:8080/api/v1/products?type=${selectedMenu.type}`,
        { signal, method: 'GET' }
      );
      const json = (await res.json()) as ApiResponse<ProductPreview[]>;
      setProductPreviews(!json.data ? [] : json.data);
    })();
  }, [selectedMenu]);

  return (
    <div className='bg-gray-300 w-3/4'>
      <h3 className='text-2xl font-extrabold m-2'>{selectedMenu.menuName}</h3>
      <div className='flex gap-4 m-3'>
        {productPreviews.map((productPreview) => {
          return <ProductCard key={productPreview.id} {...productPreview} />;
        })}
      </div>
    </div>
  );
};

export default ProductList;
