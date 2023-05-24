import React, {useEffect, useState} from "react";
import './FriendDetail.css';
import {useParams} from "react-router-dom";
import axios from "axios";
import {Friend} from "../../model/Friend";

export default function FriendDetail() {

    const [friend, setFriend] = useState<Friend>()
    const {id} = useParams<{ id: string }>()

    useEffect(() => {
        if (id) {
            loadFriendById(id)
        } //eslint-disable-next-line
    }, [id])

    function loadFriendById(id: string) {
        axios.get('/api/friend/' + id)
            .then((response) => {
                setFriend(response.data)
            })
            .catch((r) => {
                console.error("Friend not found" + r)
            })
    }

    return (
        <div className="friend-detail">
            {
                friend
                    ? <div className="detail-content">
                        <p>{friend.id}</p>
                        <p>{friend.name}</p>
                        <p>{friend.url}</p>
                    </div>
                    : <div>Loading...</div>
            }
        </div>
    )
}