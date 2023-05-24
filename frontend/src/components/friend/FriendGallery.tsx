import {Friend} from "../../model/Friend";
import FriendCard from "./FriendCard";

type FriendGalleryProps = {
    friends: Friend[]
    deleteFriend: (id: string) => void
    updateFriend: (friend: Friend) => void
}
export default function FriendGallery(props: FriendGalleryProps) {
    return (
            <div className="friend-gallery">
                <div>
                    <h2>All Friends</h2>
                    {
                        props.friends.map((friend)=><FriendCard key={friend.id} friend={friend}
                                                               deleteFriend={props.deleteFriend}
                                                               updateFriend={props.updateFriend}/>)}
                </div>
            </div>
    )
}