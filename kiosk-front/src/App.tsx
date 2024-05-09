import { Route, Routes } from 'react-router-dom';
import './App.css';
import Randing from './pages/Randing';
import CheckTakeOut from './pages/CheckTakeout';
import RecMenu from './pages/RecMenu';
import Point from './pages/Point';
import OrderCheck from './pages/OrderCheck';
import Pay from './pages/Pay';
import OrderComplete from './pages/OrderComplete';
import ShowPoint from './pages/ShowPoint';

function App() {
  return (
    <Routes>
      <Route path='/' element={<Randing />} />
      <Route path='/checkTakeout' element={<CheckTakeOut />} />
      <Route path='/main' element={<CheckTakeOut />} />
      <Route path='/recmenu' element={<RecMenu />} />
      <Route path='/point' element={<Point />} />
      <Route path='/showPoint' element={<ShowPoint />} />
      <Route path='/checkOrder' element={<OrderCheck />} />
      <Route path='/pay' element={<Pay />} />
      <Route path='/complete' element={<OrderComplete />} />
    </Routes>
  );
}

export default App;
