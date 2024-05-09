function AdminProductList() {
  const totalProduct = 3;

  return (
    <div>
      <p className='font-bold mb-5'>
        총 <span className='font-bold text-red-500'>{totalProduct}</span>개의
        상품이 있습니다.
      </p>
      {/* product component */}
      <div className='flex mx-3 border-2'>
        <div className='border-2 m-2'>상품 1</div>
        <div className='border-2 m-2'>상품 2</div>
        <div className='border-2 m-2'>상품 3</div>
        <div className='border-2 m-2'>상품 4</div>
      </div>
    </div>
  );
}

export default AdminProductList;
