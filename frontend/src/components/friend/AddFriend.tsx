import React, {FormEvent, useState} from "react";
import "./AddFriend.css"
import {useNavigate} from "react-router-dom";
import {NewFriend} from "../../model/Friend";

type AddFriendProps = {
    addFriend: (newFriend: NewFriend , image: File | undefined) => void
}
export default function AddFriend(props: AddFriendProps) {

    const [name, setName] = useState("")
    const [image, setImage] = useState<File>();
    const navigate = useNavigate();

    function onSaveFriend(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        const newFriend: NewFriend = {id: "unique-id", name: name, url: ""}
        props.addFriend(newFriend, image)
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
                    <input type="file"
                           className="imagefile"
                           placeholder="a picture of your friend"
                           onChange={(event) => {
                               if (event.target.files) {
                                   setImage(event.target.files[0])
                               }
                           }
                           }/>
                </div>
                <br/>
                <button className="addButton">save Friend</button>
            </form>
        </div>
    )
}