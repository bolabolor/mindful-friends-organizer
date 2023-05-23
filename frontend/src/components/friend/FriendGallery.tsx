import {Friend} from "../../model/Friend";
import FriendCard from "./FriendCard";

type FriendGalleryProps = {
    friends: Friend[]
}
export default function CarGallery(props: FriendGalleryProps) {
    return (
            <div className="friend-gallery">
                <div>
                    { props.friends.map ((friend)=><FriendCard key={friend.id} friend={friend}/>)}
                </div>
            </div>
    )
}