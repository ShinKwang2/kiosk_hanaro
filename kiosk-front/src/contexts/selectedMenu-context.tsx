import { PropsWithChildren, createContext, useContext, useState } from 'react';
import { MenuTitle } from '../types/types';

type SelectedMenuContextProps = {
  selectedMenu: MenuTitle;
  changeMenu: (menuTitle: MenuTitle) => void;
};

const SelectedMenuContext = createContext<SelectedMenuContextProps>({
  selectedMenu: {
    menuName: '추천메뉴',
    tapIndex: 0,
    type: 'recommended',
  },
  changeMenu: () => {},
});

export const SelectedMenuProvider = ({ children }: PropsWithChildren) => {
  const [selectedMenu, setMenu] = useState<MenuTitle>({
    menuName: '추천메뉴',
    tapIndex: 0,
    type: 'recommended',
  });

  const changeMenu = (menuTitle: MenuTitle) => {
    setMenu(menuTitle);
  };

  return (
    <SelectedMenuContext.Provider value={{ selectedMenu, changeMenu }}>
      {children}
    </SelectedMenuContext.Provider>
  );
};

// eslint-disable-next-line react-refresh/only-export-components
export const useSelectedMenu = () => useContext(SelectedMenuContext);
