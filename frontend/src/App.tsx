import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./components/LoginPage";
import SignupPage from './components/SignupPage';
import useUser from "./hook/useUser";

function App() {
    const {user, login} = useUser()
  return (
      <BrowserRouter>
       <div className="App">
        <Routes>
            <Route path='/signup' element={<SignupPage/>}/>
            <Route path='/login' element={<LoginPage onLogin={login}/>}/>

        </Routes>
       </div>
      </BrowserRouter>
  );
}
export default App;