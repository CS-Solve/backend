package com.server.computerscience;

import com.server.computerscience.login.aspect.LoginAspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Import(LoginAspect.class)
@EnableAspectJAutoProxy
public class ViewControllerTest extends ControllerTest {
}
