import React, {Component} from "react";
import SportService from "../../services/sport-service";
import {Grid} from "@mui/material";
import Box from "@mui/material/Box";
import SportCard from "../sport-card/sport-card";

export default class SportList extends Component {
    
    constructor(props) {
        super(props);
        this.fetchSports = this.fetchSports.bind(this);
        this.state = {
            sports: []
        }
    }
    
    componentDidMount() {
        this.fetchSports();
    }
    
    async fetchSports() {
        await SportService.getAllSports()
            .then(response => {
                this.setState({
                    sports: response.data
                })
            })
            .catch(e => console.log(e))
    }
    
    render() {
        const {sports} = this.state;
        
        return (
            <Box sx={{ flexGrow: 1 }} padding={6}>
                <Grid container spacing={1}>
                    {sports.map((sport, i) => {
                            if (i % 4 === 0) {
                                return (
                                    <Grid container item spacing={3} marginBottom={3}>
                                        <Row sports={sports.slice(i, i + 4)}/>
                                    </Grid>
                                )
                            }
                        }
                    )}
                </Grid>
            </Box>
        );
    }

}

function Row(props) {

    const {sports} = props;

    return (
        <>
            {sports.map(sport =>
                <Grid item xs={3}>
                    <SportCard
                        img={sport.thumbnailUrl}
                        name={sport.name}
                    />
                </Grid>
            )}
        </>
    );
}