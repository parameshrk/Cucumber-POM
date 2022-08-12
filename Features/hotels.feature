Feature: All bookings.com scenarios

 # https://www.booking.com/searchresults.html?aid=355028&sid=3a835ee8169eab4b6a1c4a9772dcba43&sb=1&sb_lp=1&src=index&src_elem=sb&error_url=https%3A%2F%2Fwww.booking.com%2Findex.html%3Faid%3D355028%26sid%3D3a835ee8169eab4b6a1c4a9772dcba43%26sb_price_type%3Dtotal%26%26&ss=Goa%2C+India&is_ski_area=&checkin_year=2022&checkin_month=9&checkin_monthday=20&checkout_year=2022&checkout_month=9&checkout_monthday=30&group_adults=2&group_children=0&no_rooms=1&b_h4u_keep_filters=&from_sf=1&dest_id=4127&dest_type=region&search_pageview_id=4554640318c301a3&search_selected=true


# 1
  @starverify
  Scenario Outline: Verify user can only view the result by selected property class
    Given I am on default locations search result screen
    When I select option for stars as <stars>
    Then I verify system displays only <stars> hotels on search result
    Examples:
      | stars   |
      | 5 stars |
      | 4 stars |
      | 3 stars |


#2

  @DistList
  Scenario: List of all of hotel within 15 km radius from a beach
    Given I am on default locations search result screen
    Then I verify system displays all hotels within "2" Km radius from a beach


#3
  @hotelsSearch
  Scenario: verify given hotel is present in the list
    Given I am on default locations search result screen
    Then I verify "Aaria Residency" is within search result