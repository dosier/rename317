package pgle;

import org.lwjgl.util.Color;
import org.peterbjornx.pgl2.terrain.TerrainSource;
import org.peterbjornx.pgl2.texture.Texture2D;
import rs2.Flo;
import rs2.MapRegion;
import sun.text.normalizer.IntTrie;

/**
 * Created by IntelliJ IDEA.
 * User: Peter
 * Date: 6/17/11
 * Time: 6:23 PM
 * Computer: Peterbjornx-PC.rootdomain.asn.local (192.168.178.27)
 */
public class RsTerrainSource implements TerrainSource {
    private MapRegion mapRegion;
    private int[][] textureMap = new int[104][104];
    private Color[][] colourMap = new Color[104][104];
    private int heightLevel = 0;

    public RsTerrainSource(MapRegion mapRegion) {
        this.mapRegion = mapRegion;
    }

    private void updateMap(){
        byte[][] underlays = mapRegion.getUnderLay()[heightLevel];
        int[][] underlaysRgb = mapRegion.getUnderlayRgb()[heightLevel];
        for (int x = 0;x < 104;x++)
            for (int z = 0;z < 104;z++){
                if (underlays[x][z] == 0)
                    underlays[x][z] = 1;
                Flo underlay = Flo.cache[underlays[x][z] - 1];
                int underlayRgb = underlaysRgb[x][z];
                colourMap[x][z] = new Color(underlayRgb >> 16,underlayRgb >> 8,underlayRgb & 0xFF);
                textureMap[x][z] = underlay.texture;
            }
    }

    public int[][] getHeightMap() {
        return mapRegion.getHeightMap()[heightLevel];
    }

    public Color[][] getColorMap() {
        return colourMap;
    }

    public int[][] getTextureMap() {
        return textureMap;
    }

    public Texture2D getTexture(int i) {
        return null;
    }

    public int getTileSize() {
        return 128;
    }

    public int getSideSize() {
        return 103;
    }
}