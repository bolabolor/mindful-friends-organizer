import {Friend} from "../../model/Friend";
import {useNavigate} from "react-router-dom";

type FriendCardProps = {
    friend : Friend
    deleteFriend: (id: string) => void
    updateFriend: (friend: Friend) => void
}
export default function FriendCard (props : FriendCardProps) {
    const navigate = useNavigate()

    function onDeleteClick() {
        props.deleteFriend(props.friend.id)
    }
    return <div className="friend-circle">
        <p>{props.friend.id}</p>
        <p>{props.friend.name}</p>
        <p>{props.friend.url}</p>
        <button onClick={() => {navigate('/friend/update/' + props.friend.id)}}>Update</button>
        <button onClick={() => {navigate('/friend/' + props.friend.id)}}>Detail</button>
        <button className="deleteButton" onClick={onDeleteClick}>Delete</button>
        <h3 className="friend-card_name">{props.friend.name}</h3>
        <h3 className="friend-card_image">{props.friend.url}</h3>
    </div>
}