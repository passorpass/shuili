package com.shuili.util;


import com.shuili.enums.ServerExceptionEnum;
import com.shuili.exception.ServiceException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletUtil {

    /**
     * 获取当前请求的request对象
     *
     * @author xuyuxiang
     * @date 2020/3/30 15:10
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new ServiceException(ServerExceptionEnum.REQUEST_EMPTY);
        } else {
            return requestAttributes.getRequest();
        }
    }

    /**
     * 获取当前请求的response对象
     *
     * @author xuyuxiang
     * @date 2020/3/30 15:10
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new ServiceException(ServerExceptionEnum.REQUEST_EMPTY);
        } else {
            return requestAttributes.getResponse();
        }
    }
}
