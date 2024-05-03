const ProductList = ({ menuTitle }) => {
  return (
    <div className='bg-gray-300 w-3/4'>
      <h3 className='text-xl'>{menuTitle}</h3>
      <div className='flex flex-wrap gap-4 m-3'>
        <div className='bg-gray-400'>프로덕트</div>
        <div className='bg-gray-400'>프로덕트</div>
        <div className='bg-gray-400'>프로덕트</div>
        <div className='bg-gray-400'>프로덕트</div>
        <div className='bg-gray-400'>프로덕트</div>
      </div>
    </div>
  );
};

export default ProductList;
