import Menu from './Menu';

const menuNames = ['추천메뉴', '버거&세트', '맥모닝', '디저트', '음료'];

const MenuList = () => {
  return (
    <div className='bg-green-400 w-1/4 flex flex-col gap-5 p-4 justify-center'>
      {/* <div className='border border-black h-1/6'>추천메뉴</div>
      <div className='border border-black h-1/6'>버거&세트</div>
      <div className='border border-black h-1/6'>맥모닝</div>
      <div className='border border-black h-1/6'>디저트</div> */}
      {/* <div className=''>음료</div> */}
      {menuNames.map((menu) => {
        return <Menu key={menu} name={menu} />;
      })}
    </div>
  );
};

export default MenuList;
