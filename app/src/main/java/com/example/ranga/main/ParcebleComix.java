package com.example.ranga.main;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.ranga.database.Comix;

public class ParcebleComix implements Parcelable
{
    private Comix comix;

    public ParcebleComix(Comix comix)
    {
        this.comix = comix;
    }

    public static final Creator<ParcebleComix> CREATOR = new Creator<ParcebleComix>() {
        @Override
        public ParcebleComix createFromParcel(Parcel parcel)
        {
            Comix tmp = new Comix();
            tmp.id = parcel.readLong();
            tmp.nameRus = parcel.readString();
            tmp.nameEng = parcel.readString();
            tmp.desc = parcel.readString();
            tmp.nameFolder = parcel.readString();
            tmp.size = parcel.readLong();
            tmp.author = parcel.readString();
            return new ParcebleComix(tmp);
        }

        @Override
        public ParcebleComix[] newArray(int i) {
            return new ParcebleComix[i];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i)
    {
        parcel.writeLong(comix.id);
        parcel.writeString(comix.nameRus);
        parcel.writeString(comix.nameEng);
        parcel.writeString(comix.desc);
        parcel.writeString(comix.nameFolder);
        parcel.writeLong(comix.size);
        parcel.writeString(comix.author);
    }

    public Comix getComix()
    {
        return comix;
    }
}
