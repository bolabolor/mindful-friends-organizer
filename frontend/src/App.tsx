import React, {useEffect, useState} from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./security/LoginPage";
import SignupPage from './security/SignupPage';
import useUser from "./hook/useUser";
import {Friend} from "./model/Friend";
import axios from "axios";
import FriendGallery from './friend/FriendGallery';

function App() {
    const {user, login} = useUser()
    const [friends, setFriends] = useState<Friend[]>([])

    useEffect(() => {
        if (user) {
            loadAllFriends();
        }
    }, [user, loadAllFriends]);
    function loadAllFriends() {
        axios.get("/api/friend")
            .then(r => {setFriends(r.data.results)
            })
            .catch((reason) => {
                console.error(reason)
            })
    }

  return (
      <BrowserRouter>
       <div className="App">
        <Routes>
            <Route path='/signup' element={<SignupPage/>}/>
            <Route path='/login' element={<LoginPage onLogin={login}/>}/>
            <Route path='/friends' element={<FriendGallery friends={friends}/>}/>

        </Routes>
       </div>
      </BrowserRouter>
  );
}
export default App;