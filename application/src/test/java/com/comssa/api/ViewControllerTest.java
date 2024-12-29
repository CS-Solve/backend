package com.comssa.api;

import com.comssa.api.member.aspect.LoginAspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Import(LoginAspect.class)
@EnableAspectJAutoProxy
public class ViewControllerTest extends ControllerTest {
}
