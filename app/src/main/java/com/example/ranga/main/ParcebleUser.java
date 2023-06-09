package com.example.ranga.main;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.ranga.database.User;

public class ParcebleUser implements Parcelable
{
    private User user;
    public ParcebleUser(User user)
    {
        this.user = user;
    }

    public static final Creator<ParcebleUser> CREATOR = new Creator<ParcebleUser>() {
        @Override
        public ParcebleUser createFromParcel(Parcel parcel)
        {
            User tmp = new User();
            tmp.id = parcel.readLong();
            tmp.login = parcel.readString();
            tmp.email = parcel.readString();
            tmp.pass = parcel.readString();
            tmp.avatar = parcel.readLong();
            tmp.status = parcel.readString();
            return new ParcebleUser(tmp);
        }

        @Override
        public ParcebleUser[] newArray(int i) {
            return new ParcebleUser[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i)
    {
        parcel.writeLong(user.id);
        parcel.writeString(user.login);
        parcel.writeString(user.email);
        parcel.writeString(user.pass);
        parcel.writeLong(user.avatar);
        parcel.writeString(user.status);
    }

    public User getUser() {return user;}
}
