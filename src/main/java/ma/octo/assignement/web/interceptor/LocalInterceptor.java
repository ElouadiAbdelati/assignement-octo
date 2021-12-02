package ma.octo.assignement.web.interceptor;

import lombok.RequiredArgsConstructor;
import ma.octo.assignement.util.messaging.LocalMessageReader;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class LocalInterceptor implements HandlerInterceptor {
    private final LocalMessageReader localMessageReader;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        localMessageReader.setLocale(request.getLocale());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
