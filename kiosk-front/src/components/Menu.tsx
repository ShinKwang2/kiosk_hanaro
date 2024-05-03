type MenuProps = {
  name: string;
};

const Menu = ({ name }: MenuProps) => {
  return <div className='border border-black h-1/6'>{name}</div>;
};

export default Menu;
