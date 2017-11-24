package com.gwf.security.core.vaildate.code.image;

import com.gwf.security.core.vaildate.code.ValidateCodeGenerator;
import com.gwf.security.core.properties.SecurityProperties;
import lombok.Data;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
        //1.生成图片对象
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width",securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),"height",securityProperties.getCode().getImage().getHeight());
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        //2.生成随机干扰条纹到图片上
        Random random = new Random();
        g.setColor(getRandColor(200,250));  //背景颜色
        g.fillRect(0,0,width,height);  //宽高
        g.setFont(new Font("Times New Roman",Font.ITALIC,20)); //字体
        g.setColor(new Color(20,20,20)); //画边框
        g.drawRect(0, 0, width - 1, height - 1);
        g.setColor(getRandColor(160, 200));
        for(int i=0;i<155;i++){  //生成图片干扰条纹
            int x =random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }

        //生成随机数
        String sRand = "";
        for(int i=0;i<securityProperties.getCode().getImage().getLength();i++){
            String rand = String.valueOf(random.nextInt(10));
            sRand+=rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand,13*i+6,16);
        }

        g.dispose();

        return new ImageCode(image,sRand,
                securityProperties.getCode().getImage().getExpireIn());
    }

    /**
     * 生成随机背景条纹
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc,int bc) {
        Random random = new Random();
        if(fc > 255) {
            fc = 255;
        }
        if(bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
