package isel.pdm.yamda.data.image;

import android.content.Context;

import java.io.File;

/**
 * Cache Images Class
 */
public class FileCache {

    private File cacheDir;
    private static final String CACHE_TAG = "yamda_images_cache";

    public FileCache(Context context) {
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            this.cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), CACHE_TAG);
        else
            this.cacheDir = context.getCacheDir();

        if (!this.cacheDir.exists())
            this.cacheDir.mkdirs();
    }

    public File getFile(String url) {
        //I identify images by hashcode. bad?
        String filename = String.valueOf(url.hashCode());
        //Another possible solution
        //String filename = URLEncoder.encode(url);
        return new File(this.cacheDir, filename);
    }

    public void clear() {
        File[] files = this.cacheDir.listFiles();
        if (files == null)
            return;

        for (File f : files)
            f.delete();
    }

}