import {Friend} from "../model/Friend";

type FriendCardProps = {
    friend : Friend
}
export default function FriendCard (props : FriendCardProps) {
    return <div className="friend-circle">
        <h3 className="friend-card_name">{props.friend.name}</h3>
        <h3 className="friend-card_image">{props.friend.url}</h3>
    </div>
}