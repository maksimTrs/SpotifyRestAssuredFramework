package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationapi.PlaylistApi;
import com.spotify.oauth2.pojo.PlayListError;
import com.spotify.oauth2.pojo.PlayListMain;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.ConfigLoader.getSingletonInstance;
import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTests extends BaseTest {

   // private volatile static String playlistId;

    @Story("Create a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("Test scenarios for Spotify APIs")
    @Test(description = "should be able to create a playlist")
    public void createNewPlayList() {

        PlayListMain playListRequest = playListBuilder(generateName(), generateDescription(), false);

        Response response = PlaylistApi.postMethod(playListRequest);
        statusAssertCode(response, StatusCode.CODE_201.CODE);
      //  assertThat(response.statusCode(), equalTo(201));

        PlayListMain responsePlayList = response.as(PlayListMain.class);
        //playlistId = responsePlayList.getId();

        assertThat(responsePlayList.getName(), equalTo(playListRequest.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(playListRequest.getDescription()));
        assertThat(responsePlayList.getJsonMemberPublic(), equalTo(playListRequest.getJsonMemberPublic()));

    }

    @Test
    public void GetPlayListByID() {

        Response response = PlaylistApi.getMethod(getSingletonInstance().getGetPlaylistId());
        statusAssertCode(response, StatusCode.CODE_200.CODE);

        PlayListMain responsePlayList = response.as(PlayListMain.class);

        assertThat(responsePlayList.getName(), not(emptyOrNullString()));
        assertThat(responsePlayList.getOwner().getId(), equalTo(getSingletonInstance().getUser()));
        assertThat(responsePlayList.getType(), equalTo("playlist"));
        assertThat(responsePlayList.getJsonMemberPublic(), equalTo(false));
    }

    @Test
    public void UpdatePlayListByID() {

        PlayListMain playListRequest = playListBuilder(generateName(), generateDescription(), false);

        Response response = PlaylistApi.putMethod(playListRequest, getSingletonInstance().getGetPlaylistId());

        statusAssertCode(response, StatusCode.CODE_200.CODE);
        assertThat(response.asString(), emptyString());
    }

    @Story("Create a playlist story")
    @Test
    public void createNewPlayListWithoutNameField() {

        PlayListMain playListRequest = playListBuilder("", generateDescription(), false);

        Response response = PlaylistApi.postMethod(playListRequest);
        statusAssertCode(response, StatusCode.CODE_400.CODE);

        PlayListError playListErrorResponse = response.as(PlayListError.class);

        assertErrorCase(playListErrorResponse, StatusCode.CODE_400.CODE, StatusCode.CODE_400.MSG);
        //assertThat(playListErrorResponse.getError().getStatus(), equalTo(400));
        //assertThat(playListErrorResponse.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Story("Create a playlist story")
    @Test
    public void createNewPlayListWithIncorrectToken() {


        PlayListMain playListRequest = playListBuilder(generateName(), generateDescription(), false);

        Response response = PlaylistApi.postMethod(playListRequest, "1123123");
        statusAssertCode(response, StatusCode.CODE_401.CODE);

        PlayListError playListErrorResponse = response.as(PlayListError.class);

        assertErrorCase(playListErrorResponse, StatusCode.CODE_401.CODE, StatusCode.CODE_401.MSG);
    }


    @Step
    private PlayListMain playListBuilder(String name, String descr, boolean isPublic) {
        return PlayListMain.builder()
                .name(name)
                .description(descr)
                .jsonMemberPublic(isPublic)
                .build();
    }

    @Step
    private void statusAssertCode(Response response, int code) {
        assertThat(response.statusCode(), equalTo(code));
    }

    @Step
    private void assertErrorCase(PlayListError playListErrorResponse, int statusCode, String errorMessage){
        assertThat(playListErrorResponse.getInnerError().getStatus(), equalTo(statusCode));
        assertThat(playListErrorResponse.getInnerError().getMessage(), equalTo(errorMessage));
    }
}
