import React, {FormEvent, useState} from "react";
import "./AddFriend.css"
import {useNavigate} from "react-router-dom";
import {NewFriend} from "../../model/Friend";

type AddFriendProps = {
    addFriend: (newFriend: NewFriend) => void
}
export default function AddFriend(props: AddFriendProps) {

    const [name, setName] = useState("")
    const navigate = useNavigate();

    function onSaveFriend(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        const newFriend: NewFriend = {name: name, url: ""}
        props.addFriend(newFriend)
        navigate("/friend")
    }

    return (
        <div>
            <form onSubmit={onSaveFriend}>
                <div className="textareaContainer">
                <textarea className="addName"
                          placeholder="your new Friend"
                          value={name}
                          onChange={(event) => {
                              setName(event.target.value)
                          }}/>
                </div>
                <br/>
                <button className="addButton">save Friend</button>
            </form>
        </div>
    )
}