create view game_athlete_count_vw as select
    r.game_id as game_id, count(a.id) as athletes from result r
    join athlete a on a.id = r.athlete_id group by r.game_id;