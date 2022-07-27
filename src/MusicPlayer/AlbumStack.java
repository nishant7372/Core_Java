package MusicPlayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AlbumStack {
    private List<Album> albumList;
    private String name;

    public AlbumStack() {
        this.albumList = new ArrayList<>();
    }

    public boolean addAlbum(String albumName)
    {
        if(findAlbum(albumName)==null)
        {
            return albumList.add(new Album(albumName));
        }
        System.out.println("Album " + albumName + " is already on the file");
        return false;
    }

    public boolean removeAlbum(String albumName)
    {
        Album existingAlbum = findAlbum(albumName);
        if(existingAlbum!=null)
        {
            albumList.remove(existingAlbum);
        }
        System.out.println("Album " + albumName + " is not on the file");
        return false;
    }

    private Album findAlbum(String albumName)
    {
        for(Album album:albumList)
        {
            if(album.getName().equals(albumName))
                return album;
        }
        return null;
    }

    public boolean addSong(String albumName,String songName,double songDuration)
    {
        Album album = findAlbum(albumName);
        if(album!=null)
        {
           return album.addSong(songName,songDuration);
        }
        System.out.println("Album " + albumName + " is not on the file");
        return false;
    }

    public boolean removeSong(String albumName,String songName)
    {
        Album album = findAlbum(albumName);
        if(album!=null)
        {
            return album.removeSong(songName);
        }
        System.out.println("Album " + albumName + " is not on the file");
        return false;
    }

    public boolean showSongList(String albumName)
    {
        Album album = findAlbum(albumName);
        if(album!=null)
        {
            System.out.println("Songs in the Album " + albumName);
            album.showSongList();
            return true;
        }
        System.out.println("Album " + albumName + " is not on the file");
        return false;
    }

    public boolean addToPlayList(String albumName, LinkedList<Song> playList,int trackNo)
    {
        Album album = findAlbum(albumName);
        if(album!=null)
        {
            return album.addToPlayList(playList,trackNo);
        }
        System.out.println("Album " + albumName + " is not on the file");
        return false;
    }

}
