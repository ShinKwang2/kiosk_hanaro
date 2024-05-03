import { MenuTitle } from '../types/types';
import MenuTab from './MenuTab';

type MenuTapListProps = {
  menuTitles: MenuTitle[];
  onClickMenu: (menuTitle: string) => void;
};

const MenuTabList = ({ menuTitles, onClickMenu }: MenuTapListProps) => {
  return (
    <div className='bg-green-400 w-1/4 flex flex-col gap-5 p-4 justify-center'>
      {/* <div className='border border-black h-1/6'>추천메뉴</div>
      <div className='border border-black h-1/6'>버거&세트</div>
      <div className='border border-black h-1/6'>맥모닝</div>
      <div className='border border-black h-1/6'>디저트</div> */}
      {/* <div className=''>음료</div> */}
      {menuTitles.map((menu) => {
        return (
          <MenuTab key={menu.tapIndex} onClickMenu={onClickMenu} {...menu} />
        );
      })}
    </div>
  );
};

export default MenuTabList;
