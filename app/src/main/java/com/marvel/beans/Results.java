    package com.marvel.beans;

    import android.os.Parcel;
    import android.os.Parcelable;

    import androidx.annotation.NonNull;

    import com.google.gson.annotations.SerializedName;

    import java.util.List;

    public class Results implements Parcelable {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("description")
        private String description;
        @SerializedName("modified")
        private String modified;
        @SerializedName("thumbnail")
        private Thumbnail thumbnail;
        @SerializedName("resourceURI")
        private String resourceURI;
        @SerializedName("comics")
        private Comics comics;
        @SerializedName("series")
        private Series series;
        @SerializedName("stories")
        private Stories stories;
        @SerializedName("events")
        private Events events;
        @SerializedName("urls")
        private List<Urls> urls;
        @SerializedName("title")
        private String title;


        protected Results(Parcel in) {
            id = in.readInt();
            name = in.readString();
            description = in.readString();
            modified = in.readString();
            thumbnail = in.readParcelable(Thumbnail.class.getClassLoader()); // read the Thumbnail object from the Parcel
            resourceURI = in.readString();
            comics = in.readParcelable(Comics.class.getClassLoader());
            series = in.readParcelable(Series.class.getClassLoader());
            stories = in.readParcelable(Stories.class.getClassLoader());
            events = in.readParcelable(Events.class.getClassLoader());
            title = in.readString();
        }

        public static final Creator<Results> CREATOR = new Creator<Results>() {
            @Override
            public Results createFromParcel(Parcel in) {
                return new Results(in);
            }

            @Override
            public Results[] newArray(int size) {
                return new Results[size];
            }
        };

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public Thumbnail getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(Thumbnail thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getResourceURI() {
            return resourceURI;
        }

        public void setResourceURI(String resourceURI) {
            this.resourceURI = resourceURI;
        }

        public Comics getComics() {
            return comics;
        }

        public void setComics(Comics comics) {
            this.comics = comics;
        }

        public Series getSeries() {
            return series;
        }

        public void setSeries(Series series) {
            this.series = series;
        }

        public Stories getStories() {
            return stories;
        }

        public void setStories(Stories stories) {
            this.stories = stories;
        }

        public Events getEvents() {
            return events;
        }

        public void setEvents(Events events) {
            this.events = events;
        }

        public List<Urls> getUrls() {
            return urls;
        }

        public void setUrls(List<Urls> urls) {
            this.urls = urls;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(name);
            parcel.writeString(description);
            parcel.writeString(modified);
            parcel.writeParcelable(thumbnail, i);
            parcel.writeString(resourceURI);
            parcel.writeParcelable(comics, i);
            parcel.writeParcelable(series, i);
            parcel.writeParcelable(stories, i);
            parcel.writeParcelable(events, i);
            parcel.writeString(title);
        }

    }
