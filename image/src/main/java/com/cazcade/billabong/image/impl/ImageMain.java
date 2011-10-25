package com.cazcade.billabong.image.impl;

import com.cazcade.billabong.image.CacheResponse;
import com.cazcade.billabong.image.ImageService;
import com.cazcade.billabong.image.ImageSize;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 * User: robertboothby
 * Date: Jul 26, 2010
 * Time: 2:50:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageMain {

    public static void main(String[] args) throws URISyntaxException {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("/spring/image-service.xml"));
        ImageService service = (ImageService) factory.getBean("imageService");
//        sendRequest(service, "http://c0021791.cdn1.cloudfiles.rackspacecloud.com/12bd4a28-8e01-4662-8faf-ca5dee21449b.png");
//        sendImageRequest(service, "http://c0021791.cdn1.cloudfiles.rackspacecloud.com/12bd4a28-8e01-4662-8faf-ca5dee21449b.png");
        sendRequest(service, "http://www.msn.com");
        sendRequest(service, "http://www.google.com");
        sendRequest(service, "http://www.amazon.com");
        sendRequest(service, "http://www.theregister.co.uk");
        sendRequest(service, "http://www.slashdot.org");
        sendRequest(service, "http://news.bbc.co.uk");
    }

    private static void sendRequest(ImageService service, String uriString) throws URISyntaxException {
        CacheResponse response = service.getCacheURI(new URI(uriString), ImageSize.CLIPPED_MEDIUM);
        System.out.println(response.getURI());
        System.out.println(response.getRefreshIndicator());
    }

    private static void sendImageRequest(ImageService service, String uriString) throws URISyntaxException {
        CacheResponse response = service.getCacheURIForImage(new URI(uriString), ImageSize.CLIPPED_MEDIUM, true);
        System.out.println(response.getURI());
        System.out.println(response.getRefreshIndicator());
    }

}
