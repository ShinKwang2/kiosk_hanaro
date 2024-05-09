import clsx from 'clsx';
import { MenuTitle } from '../../types/types';
import { useSelectedMenu } from '../../contexts/selectedMenu-context';
import { memo } from 'react';

type MenuTabProps = {
  menuTitle: MenuTitle;
};

const MenuTab = memo<MenuTabProps>(({ menuTitle }: MenuTabProps) => {
  const { menuName, tapIndex } = menuTitle;
  const { selectedMenu, changeMenu } = useSelectedMenu();

  const menuTabClasses = clsx('border border-black h-1/6', {
    'bg-sky-500': selectedMenu.menuName === menuName,
  });

  return (
    <div
      role='button'
      onClick={() => changeMenu(menuTitle)}
      onKeyDown={(e) => {
        if (e.key === 'Enter' || e.key === ' ') {
          changeMenu(menuTitle);
        }
      }}
      tabIndex={tapIndex}
      className={menuTabClasses}
    >
      {menuName}
    </div>
  );
});

MenuTab.displayName = 'MenuTab';

export default MenuTab;
