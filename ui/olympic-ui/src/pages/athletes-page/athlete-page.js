import React, {Component} from 'react';

import {withStyles} from '@mui/styles';
import {styles} from "../../css-common";

import AthleteService from "../../services/athlete-service";
import {Container, Grid, Stack, Avatar, Typography} from "@mui/material";
import AthleteMainInfo from "../../components/athlete-main-info/athlete-main-info";

class AthletePage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: null
        }
    }

    componentDidMount() {
        this.getAthleteInfo(this.props.match.params.id)
    }

    getAthleteInfo = (id) => {
        AthleteService.getAthleteById(id)
            .then(response => {
                console.log(response.data)
                this.setState({data: response.data})
            })
            .catch(e => console.log(e));
    }

    onSelectGame = (id) => {
        window.location.assign(`/game/${id}`);
    }

    render() {

        const {data} = this.state;

        const {classes} = this.props;

        return (
            <Container maxWidth="lg">
                <h1 style={{"text-transform":"uppercase", "text-align":"center", "margin-top": "80px"}}>Athlete Info</h1>
                <Grid container spacing={2} style={{"margin-bottom":"10px"}}>
                    <Grid item xs={4} style={{"textAlign":"center"}}>
                        <Stack direction="row" spacing={2}>
                            <Avatar
                                alt={data && data.surname}
                                src=""
                                sx={{ width: 256, height: 256 }}
                                style={{"margin": "10px auto"}}
                            />
                        </Stack>
                        <Typography className={classes.athleteName}>{data && data.name} {data && data.surname}</Typography>
                    </Grid>
                    { data &&
                        <Grid item xs={8}>
                            <AthleteMainInfo
                                data={data}
                                onSelectGame={this.onSelectGame}
                            />
                        </Grid>
                    }
                </Grid>
            </Container>
        );
    }

}

export default withStyles(styles)(AthletePage);