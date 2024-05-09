import {
  PropsWithChildren,
  createContext,
  useCallback,
  useContext,
  useState,
} from 'react';
import { MenuTitle } from '../types/types';

type SelectedMenuContextProps = {
  selectedMenu: MenuTitle;
  changeMenu: (menuTitle: MenuTitle) => void;
};

const SelectedMenuContext = createContext<SelectedMenuContextProps>({
  selectedMenu: {
    menuName: '버거&세트',
    tapIndex: 1,
    type: 'hamburger',
  },
  changeMenu: () => {},
});

export const SelectedMenuProvider = ({ children }: PropsWithChildren) => {
  const [selectedMenu, setMenu] = useState<MenuTitle>({
    menuName: '버거&세트',
    tapIndex: 1,
    type: 'hamburger',
  });

  const changeMenu = useCallback((menuTitle: MenuTitle) => {
    setMenu(menuTitle);
  }, []);

  return (
    <SelectedMenuContext.Provider value={{ selectedMenu, changeMenu }}>
      {children}
    </SelectedMenuContext.Provider>
  );
};

// eslint-disable-next-line react-refresh/only-export-components
export const useSelectedMenu = () => useContext(SelectedMenuContext);
