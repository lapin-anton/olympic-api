import React, {Component} from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import {CardActionArea} from '@mui/material';

import {withStyles} from '@mui/styles';

import {styles} from "../../css-common";

class GameCard extends Component {

    constructor(props) {
        super(props);

    }

    showGamePage = () => {
        window.location.assign(`/game/${this.props.id}`);
    }

    render() {
        const {img, city, year, season, classes} = this.props;

        return (
            <Card>
                <CardActionArea onClick={this.showGamePage}>
                    <CardMedia
                        component="img"
                        alt={city}
                        sx={{width:'inherit'}}
                        image={img}
                    />
                    <CardContent className={classes.cardContent}>
                        <Typography gutterBottom variant="h5" component="div">
                            {city}
                        </Typography>
                        <Typography variant="h5" color="div">
                            {year}
                        </Typography>
                        <Typography variant="body2" color="text.secondary">
                            {season}
                        </Typography>
                    </CardContent>
                </CardActionArea>
            </Card>
        );
    }


}

export default withStyles(styles) (GameCard);