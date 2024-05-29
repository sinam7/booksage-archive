import {Card, CardContent} from "@/components/ui/card";
import React from "react";
import {BookCard} from "@/components/component/bookCard";

interface BookColumnProps {
    name: string;
}

export function BookColumn({name}: BookColumnProps) {
    return (
        <div className="space-y-4 border-r pr-8">
            <h2 className="text-2xl font-bold">{name}</h2>
            <Card>

                <CardContent className="space-y-2 mt-4">
                    <BookCard title={"Feature 1"} author={"Author"}
                              publisher={"Publisher"} price={"9,000원"}
                              imageSrc={""} href={"/"}
                              isAvailable={true}/>
                    <BookCard title={"Feature 1"} author={"Author"}
                              publisher={"Publisher"} price={"9,000원"}
                              imageSrc={""} href={"/"}
                              isAvailable={true}/>
                    <BookCard title={"Feature 1"} author={"Author"}
                              publisher={"Publisher"} price={"9,000원"}
                              imageSrc={""} href={"/"}
                              isAvailable={true}/>
                    <BookCard title={"Feature 1"} author={"Author"}
                              publisher={"Publisher"} price={"9,000원"}
                              imageSrc={""} href={"/"}
                              isAvailable={true}/>
                    <BookCard title={"Feature 1"} author={"Author"}
                              publisher={"Publisher"} price={"9,000원"}
                              imageSrc={""} href={"/"}
                              isAvailable={true}/>

                </CardContent>
            </Card>
        </div>
    );
}