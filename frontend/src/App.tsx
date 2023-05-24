import React, {useCallback, useEffect, useState} from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./security/LoginPage";
import SignupPage from './security/SignupPage';
import useUser from "./hook/useUser";
import {Friend, NewFriend} from "./model/Friend";
import axios from "axios";
import FriendGallery from './components/friend/FriendGallery';
import Header from "./components/Header";
import ProtectedRoutes from "./components/ProtectedRoutes";
import FriendDetail from "./components/friend/FriendDetail";
import AddFriend from "./components/friend/AddFriend";
import UpdateFriend from "./components/friend/UpdateFriend";

function App() {
    const {user, login} = useUser()
    const [friends, setFriends] = useState<Friend[]>([])
    const loadAllFriends = useCallback(() => {
        axios
            .get("/api/friend")
            .then((response) => {
                setFriends(response.data.results);
            })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    useEffect(() => {
        if (user) {
            loadAllFriends();
        }
    }, [user, loadAllFriends]);

    /*function loadAllFriends() {
        axios.get("/api/friend")
            .then(r => {setFriends(r.data.results)
            })
            .catch((reason) => {
                console.error(reason)
            })
    }*/
    function addFriend(friendToAdd: string) {
        const sendFriend: NewFriend = {
            id : "",
            name: friendToAdd,
            url: ""
        }
        axios.post("/api/friend", sendFriend)
            .then((addFriendResponse) => {

                setFriends([...friends, addFriendResponse.data])
            })
            .catch((error) => {
                error("Unknown Error, try again later! " + error.response.statusText, {autoClose: 10000})
            })
    }

    function deleteFriend(id: string) {
        axios.delete('/api/friend/' + id)
            .then(() => {
                setFriends(friends.filter((friend) => friend.id !== id))
            })
            .catch((r) => {
                console.error(r)
            })
    }

    function updateFriend(friend: Friend) {
        axios.put(`/api/friend/${friend.id}`, friend)
            .then((putRecipeResponse) => {
                setFriends(friends.map(helloFriend => {
                    if (helloFriend.id === friend.id) {
                        return putRecipeResponse.data
                    } else {
                        return helloFriend
                    }
                }))
            })
            .catch(console.error)
    }

  return (
      <BrowserRouter>
            <Header user={user}/>
            <div className="App">
                <Routes>
                    <Route path='/signup' element={<SignupPage/>}/>
                    <Route path='/login' element={<LoginPage onLogin={login}/>}/>
                    <Route element={<ProtectedRoutes user={user}/>}>
                        <Route path="/friend/:id" element={<FriendDetail/>}/>
                        <Route path='/friend' element={<FriendGallery friends={friends} deleteFriend={deleteFriend} updateFriend={updateFriend}/>}/>
                        <Route path='/friend/add'
                           element={<AddFriend addFriend={addFriend}/>}/>
                        <Route path='/friend/update/:id'
                           element={<UpdateFriend updateFriend={updateFriend}/>}/>
                    </Route>
                </Routes>
            </div>
      </BrowserRouter>
  );
}
export default App;