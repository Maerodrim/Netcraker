package com.example.laba.rmi;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Blurring extends UnicastRemoteObject implements Bluer {
    public Blurring() throws RemoteException {
        super();
    }

    @Override
    public byte[] blurring(byte[] byteOfImage) throws RemoteException {
        BufferedImage image = getBufferedImage(byteOfImage);

        int h = image.getHeight();
        int w = image.getWidth();

        BufferedImage result = new BufferedImage(w, h, image.getType());

        WritableRaster imageRaster = image.getRaster();
        WritableRaster resultRaster = result.getRaster();
        for (int s = 0; s < 100; s++) {
            for (int i = 1; i < w - 1; i++) {
                for (int j = 1; j < h - 1; j++) {
                    int[] r = new int[9];
                    int[] g = new int[9];
                    int[] b = new int[9];
                    for (int m = -1; m < 2; m++) {
                        for (int n = -1; n < 2; n++) {
                            int[] pixel = imageRaster.getPixel(i + m, j + n, new int[4]);

                            r[(m + 1) * (n + 1)] = pixel[0];
                            g[(m + 1) * (n + 1)] = pixel[1];
                            b[(m + 1) * (n + 1)] = pixel[2];
                        }
                    }
                    r=sort9(r);
                    g=sort9(g);
                    b=sort9(b);
                    resultRaster.setPixel(i, j, new int[]{r[4], g[4], b[4], 255});
                }
            }
        }
        result.setData(resultRaster);
        return byteArrayOfImage(result);
    }

    public byte[] byteArrayOfImage(BufferedImage image) {
        try {
            byte[] byteOfImage;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byteOfImage = baos.toByteArray();
            return byteOfImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage getBufferedImage(byte[] byteOfImage) {
        try {
            return ImageIO.read(new ByteArrayInputStream(byteOfImage));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int[] sort9(int[] mass) {
        for (int i = 0; i < 8; i++) {
            for (int j = 1; j < 9; j++) {
                if (mass[i]>mass[j]){
                    mass[i]+=mass[j];
                    mass[j]=-1*(mass[j]-mass[i]);
                    mass[i]-=mass[j];
                }
            }
        }
        return mass;
    }
}