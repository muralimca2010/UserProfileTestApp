package com.hy.userprofile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class UserResponse implements Parcelable {

  @SerializedName("page")
  @Expose
  private Integer page;
  @SerializedName("per_page")
  @Expose
  private Integer perPage;
  @SerializedName("total")
  @Expose
  private Integer total;
  @SerializedName("total_pages")
  @Expose
  private Integer totalPages;
  @SerializedName("data")
  @Expose
  private List<com.hy.userprofile.model.User> users = null;

  @SerializedName("url")
  @Expose
  private String url;

  @SerializedName("text")
  @Expose
  private String text;

  public final static Creator<UserResponse> CREATOR = new Creator<UserResponse>() {

    @SuppressWarnings({
        "unchecked"
    })
    public UserResponse createFromParcel(Parcel in) {
      return new UserResponse(in);
    }

    public UserResponse[] newArray(int size) {
      return (new UserResponse[size]);
    }
  };

  protected UserResponse(Parcel in) {
    this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.perPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
    in.readList(this.users, (Object.class.getClassLoader()));
    this.url = ((String) in.readValue(String.class.getClassLoader()));
    this.text = ((String) in.readValue(String.class.getClassLoader()));
  }

  public UserResponse() {
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getPerPage() {
    return perPage;
  }

  public void setPerPage(Integer perPage) {
    this.perPage = perPage;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public List<com.hy.userprofile.model.User> getUsers() {
    return users;
  }

  public void setUsers(List<com.hy.userprofile.model.User> data) {
    this.users = data;
  }

  public String getUrl() { return url;}

  public void setUrl(String url) {this.url = url; }

  public String getText() { return text;}

  public void setText(String text) {this.text = text; }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(page);
    dest.writeValue(perPage);
    dest.writeValue(total);
    dest.writeValue(totalPages);
    dest.writeList(users);
    dest.writeValue(url);
    dest.writeValue(text);
  }

  public int describeContents() {
    return 0;
  }
}