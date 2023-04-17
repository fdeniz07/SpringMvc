package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.tpe") // ("com.tpe") opsiyonel, eger bir tane ise defaultu bu.
@EnableWebMvc //MVC icin config ayarlarini etkinlestirmek icin
public class WebMvcConfig implements WebMvcConfigurer {

     @Bean //view name'e karsilik gelen view dosyasinin cözümlenmesini: viewResolver
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class); //JavaStandartTagLibrary:JSP dosyalari icerisinde daha az kod yazmamizi saglar.
         resolver.setPrefix("/WEB-INF/views/"); //view dosyalarinin yerini belirtiyoruz
         resolver.setSuffix(".jsp"); //view dosyalarinin uzantisi
        return resolver;
    }

    //css,image statik olan kaynaklarin dispatch'lere gönderilmesine gerek yok
    //http://localhost:8080/SpringMvc/resources/css/style.css
   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
         registry.addResourceHandler("/resources/**"). //bu pathdeki kaynaklari statik olarak sun
                 addResourceLocations("/resources/"). //kaynaklarin yeri
                 setCachePeriod(0); //cache'leme icin belirli bir periyod süresi verilebilir.
   }



}
