package com.arc.gpts.tech.bdddemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(OutputCaptureExtension.class)
class ControllerExceptionAspectTest {

    @Test
    void onExceptionIsCalledWhenAControllerMethodFails(CapturedOutput output) {
        BoomController proxy = proxy(new BoomController());

        assertThatThrownBy(() -> proxy.boom("x"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("boom");
        assertThat(output).contains("BoomController.boom(..) failed with args [x]: java.lang.IllegalStateException: boom");
    }

    @Test
    void onExceptionIsNotCalledForOtherClasses(CapturedOutput output) {
        BoomService proxy = proxy(new BoomService());

        assertThatThrownBy(proxy::boom).isInstanceOf(IllegalStateException.class);
        assertThat(output).doesNotContain("failed with args");
    }

    private static <T> T proxy(T target) {
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        factory.setProxyTargetClass(true);
        factory.addAspect(new ControllerExceptionAspect());
        return factory.getProxy();
    }

    static class BoomController {
        public String boom(String arg) {
            throw new IllegalStateException("boom");
        }
    }

    static class BoomService {
        public void boom() {
            throw new IllegalStateException("boom");
        }
    }

}
