package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;


//@Value
@Getter
@Setter
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties(allowSetters = true, value = {"collaborative"})
public class PlayListMain {

    @JsonProperty("owner")
    private Owner owner;

    @JsonProperty("images")
    private List<Object> images;

    @JsonProperty("snapshot_id")
    private String snapshotId;

    //@JsonIgnore
    // @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("collaborative")
    private Boolean collaborative;

    @JsonProperty("description")
    private String description;

    @JsonProperty("primary_color")
    private Object primaryColor;

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("tracks")
    private Tracks tracks;

    @JsonProperty("followers")
    private Followers followers;

    @JsonProperty("public")
    private Boolean jsonMemberPublic;

    @JsonProperty("name")
    private String name;

    @JsonProperty("href")
    private String href;

    @JsonProperty("id")
    private String id;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

}