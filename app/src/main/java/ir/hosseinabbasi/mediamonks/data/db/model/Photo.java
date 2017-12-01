package ir.hosseinabbasi.mediamonks.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Photo implements Parcelable {

	@SerializedName("albumId")
	private int albumId;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("thumbnailUrl")
	private String thumbnailUrl;

	public void setAlbumId(int albumId){
		this.albumId = albumId;
	}

	public int getAlbumId(){
		return albumId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setThumbnailUrl(String thumbnailUrl){
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}

	@Override
	public String toString(){
		return
				"Photo{" +
						"albumId = '" + albumId + '\'' +
						",id = '" + id + '\'' +
						",title = '" + title + '\'' +
						",url = '" + url + '\'' +
						",thumbnailUrl = '" + thumbnailUrl + '\'' +
						"}";
	}

	protected Photo(Parcel in) {
		albumId = in.readInt();
		id = in.readInt();
		title = in.readString();
		url = in.readString();
		thumbnailUrl = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(albumId);
		dest.writeInt(id);
		dest.writeString(title);
		dest.writeString(url);
		dest.writeString(thumbnailUrl);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
		@Override
		public Photo createFromParcel(Parcel in) {
			return new Photo(in);
		}

		@Override
		public Photo[] newArray(int size) {
			return new Photo[size];
		}
	};
}