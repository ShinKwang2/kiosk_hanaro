type MenuTabProps = {
  menuName: string;
  isSelected: boolean;
  tapIndex: number;
  onClickMenu: (menuTitle: string) => void;
};

const MenuTab = (props: MenuTabProps) => {
  const { menuName, isSelected, tapIndex, onClickMenu } = props;

  return (
    <div
      role='button'
      onClick={() => onClickMenu(menuName)}
      onKeyDown={(e) => {
        if (e.key === 'Enter' || e.key === ' ') {
          onClickMenu(menuName);
        }
      }}
      tabIndex={tapIndex}
      className='border border-black h-1/6'
    >
      {menuName}
    </div>
  );
};

export default MenuTab;
