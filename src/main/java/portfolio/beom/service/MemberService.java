package portfolio.beom.service;


import portfolio.beom.dto.request.LoginMemberRequest;
import portfolio.beom.dto.request.SaveMemberRequest;
import portfolio.beom.dto.response.LoginMemberResponse;
import portfolio.beom.dto.response.SaveMemberResponse;

public interface MemberService {


    SaveMemberResponse save(SaveMemberRequest request);

    LoginMemberResponse login(LoginMemberRequest loginMemberRequest);
}
