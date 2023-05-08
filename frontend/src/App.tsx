import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./components/LoginPage";
import SignupPage from './components/SignupPage';

function App() {
  return (
      <BrowserRouter>
       <div className="App">
        <Routes>
            <Route path='/signup' element={<SignupPage/>}/>
            <Route path='/login' element={<LoginPage/>}/>

        </Routes>
       </div>
      </BrowserRouter>
  );
}

export default App;
