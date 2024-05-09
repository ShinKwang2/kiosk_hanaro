import { MenuTitle } from '../../types/types';
import MenuTab from './MenuTab';

type MenuTapListProps = {
  menuTitles: MenuTitle[];
};

const MenuTabList = ({ menuTitles }: MenuTapListProps) => {
  return (
    <div className='bg-green-400 w-1/4 flex flex-col gap-5 p-4 justify-center'>
      {menuTitles.map((menu) => {
        return <MenuTab key={menu.tapIndex} menuTitle={menu} />;
      })}
    </div>
  );
};

export default MenuTabList;
