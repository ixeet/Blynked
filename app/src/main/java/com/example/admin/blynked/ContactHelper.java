package com.example.admin.blynked;

/**
 * Created by Admin on 30-06-2015.
 */

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.PhoneLookup;

public class ContactHelper {

    public static Cursor getContactCursor(ContentResolver contactHelper,
                                          String startsWith) {

        String[] projection = { Phone._ID,
                Phone.DISPLAY_NAME,
                Phone.NUMBER };
        Cursor cur = null;

        try {
            if (startsWith != null && !startsWith.equals("")) {
                cur = contactHelper.query(
                        Phone.CONTENT_URI,
                        projection,
                        Phone.DISPLAY_NAME
                                + " like \"" + startsWith + "%\"", null,
                        Phone.DISPLAY_NAME
                                + " ASC");
            } else {
                cur = contactHelper.query(
                        Phone.CONTENT_URI,
                        projection, null, null,
                        Phone.DISPLAY_NAME
                                + " ASC");
            }
            cur.moveToFirst();

        } catch (Exception e) {
            e.printStackTrace();
            cur.close();
        }

        return cur;
    }

    public static long getContactID(ContentResolver contactHelper,
                                     String number) {
        Uri contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));

        String[] projection = { PhoneLookup._ID };
        Cursor cursor = null;

        try {
            cursor = contactHelper.query(contactUri, projection, null, null,
                    null);

            if (cursor.moveToFirst()) {
                int personID = cursor.getColumnIndex(PhoneLookup._ID);
                return cursor.getLong(personID);
            }

            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }

        return -1;
    }



}