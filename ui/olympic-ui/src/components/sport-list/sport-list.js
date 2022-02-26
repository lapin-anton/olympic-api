import React, {Component} from "react";
import SportService from "../../services/sport-service";
import {Grid} from "@mui/material";
import Box from "@mui/material/Box";
import SportCard from "../sport-card/sport-card";

import { withStyles } from '@mui/styles';

import {styles} from "../../css-common";

class SportList extends Component {
    
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
        const {classes} = this.props;
        return (
            <Box sx={{ flexGrow: 1 }} padding={6}>
                <h1 className={classes.listHead}>Olympics Sport List</h1>
                <Grid container spacing={1}>
                    {sports.map((sport, i) => {
                            if (i % 6 === 0) {
                                return (
                                    <Grid container item spacing={3} marginBottom={3} >
                                        <Row className={classes.listRow}
                                            sports={sports.slice(i, i + 6)}
                                            classes={classes}
                                        />
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

    const {sports, classes} = props;

    return (
        <>
            {sports.map(sport =>
                <Grid item xs={2} className={classes.listItem}>
                    <SportCard
                        img={sport.thumbnailUrl}
                        name={sport.name}
                    />
                </Grid>
            )}
        </>
    );
}

export default withStyles(styles)(SportList);