import {ChangeEvent, FormEvent, useEffect, useState} from "react";
import "./AddFriend.css"
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import {Friend} from "../../model/Friend";

type UpdateFriendProps = {
    updateFriend: (newFriend: Friend) => void
}
export default function UpdateFriend(props: UpdateFriendProps) {

    const initialState: Friend = {id: "", name: "", url: ""}

    const [friend, setFriend] = useState<Friend>(initialState)
    const {id} = useParams<{ id: string }>()

    useEffect(() => {
        if (id) {
            loadFriendById(id)
        }
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

    const navigate = useNavigate();


    function onSaveFriend(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        if (id) {
            props.updateFriend(friend);
            navigate("/friend")
        }
    }

    function onChange(event: ChangeEvent<HTMLInputElement>) {
        const targetName: string = event.target.name;
        const value: string = event.target.value;
        if (id) {
            setFriend(
                {...friend, id: id, [targetName]: value}
            )
        }
    }

    return (
        <div className="updateFriend">
            <form onSubmit={onSaveFriend}>
                <input className="updateInput" type="text" name="name" placeholder={friend.name} value={friend.name}
                       onChange={onChange}/>
                <input className="updateInput" type="text" name="image url" placeholder={friend.url}
                       value={friend.url}
                       onChange={onChange}/>
                <button className="updateButton">Update</button>
            </form>
        </div>
    )
}