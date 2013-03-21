#!/usr/bin/env ruby

require 'net/http'
require 'uri'

DEFULAT_LINKS = 10000
links = if ARGV[0]
          eval(ARGV[0])
        else
          DEFULAT_LINKS
        end

link_value = nil

Net::HTTP.start('127.0.0.1', 8098) { |http|
  link_value = (1..links).map{ |link_no|
    puts link_no
    obj_name = sprintf("f%08d" , link_no)
    req = Net::HTTP::Put.new("/buckets/people/keys/#{obj_name}")
    res = http.request(req, obj_name)
    res.value
    sprintf("</buckets/people/keys/#{obj_name}>; riaktag=\"friend\"", link_no)
  }.join(", ")
}

http = Net::HTTP.new('127.0.0.1', 8098)
req = Net::HTTP::Put.new("/buckets/people/keys/many_links_#{links}",
  { "Link" => link_value })
data = "object with #{links} links"
res = http.request(req, data)
p res
res.value

