package com.study.computerscience;

import com.api.computerscience.login.aspect.LoginAspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Import(LoginAspect.class)
@EnableAspectJAutoProxy
public class ViewControllerTest extends ControllerTest {
}
