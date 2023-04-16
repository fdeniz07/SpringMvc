package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xml yerine bu class'i kullaniyoruz.
//Dispatcher Servlet'in tanimlanmasi, konfigürasyonu ile basliyoruz.
//AbstractAnnotationConfigD... Dispatcher Servlet'in baslatilmasini, config ayarlarinin bulundugu dosya yerinin gösterilmesini saglar.
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
        Dispatcher : Servlet WebAppContext-->Controller,Handler Mapping, View Resolver
                     Root WebAppContext-->services, repositories
    */

    @Override //DB'ye erisim icin gerekli config ayarlarini iceren class
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootContextConfig.class};
    }

    @Override //Controlle,Handler Mapping,View Resolver(SpringMVC) ile ilgili config
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override //Hangi URL ile gelen istekler servlet tarafindan karsilanacak
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
